package com.dit.himachal.apicontroller;

import com.dit.himachal.HTTP;
import com.dit.himachal.entities.*;
import com.dit.himachal.externalservices.SMSServices;
import com.dit.himachal.modals.*;
import com.dit.himachal.payload.UploadFileResponse;
import com.dit.himachal.repositories.VahanLogsRepository;
import com.dit.himachal.services.*;
import com.dit.himachal.utilities.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.qrcode.WriterException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class API {

    private static final Logger logger = LoggerFactory.getLogger(API.class);

    @Autowired
    private StatesService statesService;

    @Autowired
    private DistrictService districtService;

    @Autowired
    private BarrierService barrierService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private VehicleUserTypeService vehicleUserTypeService;

    @Autowired
    private VehicleOwnerEntriesService entriesService;

    @Autowired
    private FileStorageService fileStorageService;

    @Autowired
    private VehicleOwnerDocumentsService vehicleOwnerDocumentsService;

    @Autowired
    private OtpService otpService;

    @Autowired
    private UserService userService;

    @Autowired
    private VehicleInOutService vehicleInOutService;

    @Autowired
    VahanLogsRepository vahanLogsRepository;


    ObjectMapper objectMapper = new ObjectMapper();

    @Transactional
    @RequestMapping(value = "/api/uploadData", method = RequestMethod.POST, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadFile(@RequestParam(required = true, value = "file") MultipartFile file,
                                        @RequestParam(required = true, value = "jsondata") String jsondata) throws IOException {

        VehicleOwnerEntries vehicleUSerEntries = null;
        Map<String, Object> map = null;

        if (file != null && jsondata != null) {
            try {
                vehicleUSerEntries = objectMapper.readValue(jsondata, VehicleOwnerEntries.class);
                //Save File
                String fileName = fileStorageService.storeFile(file);
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/downloadFile/")
                        .path(fileName)
                        .toUriString();


                //Generate ID CARD NUMBER  HP/BARRIERID/Number
                vehicleUSerEntries.setIdCardNumber("REGD.NO/HP/" + barrierService.getBarrierName(vehicleUSerEntries.getBarriermaster().getBarrierId()) + "/" + entriesService.getIdCardNumberSequence());
                vehicleUSerEntries.setVehicleOwnerImageName(fileName);

                vehicleUSerEntries.setMobileInformation("");
                vehicleUSerEntries.setOtherInformation("");
                //Save Vehicle Entries
                Long ID = entriesService.saveVehicleOwnerEntries(vehicleUSerEntries);
                System.out.println(ID);
                String generatePdfUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/generateqrcode/")
                        .path(Utilities.base64Encode(Long.toString(ID)))
                        .toUriString();
                System.out.println(generatePdfUrl);
                int id_ = ID.intValue();
                VehicleOwnerDocuments vehicleOwnerDocuments = new VehicleOwnerDocuments();
                vehicleOwnerDocuments.setActive(true);
                vehicleOwnerDocuments.setUploadedBy(vehicleUSerEntries.getDataEnteredBy());
                vehicleOwnerDocuments.setVehicleImageOwner(file.getBytes());
                vehicleOwnerDocuments.setVehicleOwnerId(id_);
                vehicleOwnerDocuments.setFileType(file.getContentType());
                String[] fileFrags = file.getOriginalFilename().split("\\.");
                String extension = fileFrags[fileFrags.length - 1];
                vehicleOwnerDocuments.setFileExtention(extension);
                vehicleOwnerDocumentsService.saveDocuments(vehicleOwnerDocuments);
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, new UploadFileResponse(fileName, fileDownloadUri, generatePdfUrl, file.getContentType(), file.getSize(), vehicleUSerEntries));
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } catch (Exception ex) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, ex.getLocalizedMessage());
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNPROCESSABLE_ENTITY);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Data Missing");
            map.put(Constants.keyMessage, Constants.valueMessage);
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = fileStorageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }


    /**
     * @return
     */
    @RequestMapping(value = "/api/states", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getStates() {
        Map<String, Object> map = null;
        try {
            List<StatesMaster> states = statesService.getStates();
            if (!states.isEmpty()) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, states);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, states);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return
     */
    @RequestMapping(value = "/api/districts/{stateId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDistricts(@PathVariable("stateId") int state_id) {
        Map<String, Object> map = null;
        try {
            List<DistrictMaster> districts = districtService.getDistricts(state_id);
            if (!districts.isEmpty()) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, districts);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, districts);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    /**
     * Get Vehicle Details Via Service
     */
    @RequestMapping(value = "/api/getVehicleDetails", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> getVehicleDetailsViaVahan(@RequestBody String jsondata ) {
        Map<String, Object> map = null;
        HTTP http = new HTTP();
        String registrationNumber = null, ip = null, userId = null;
        if(jsondata!=null && !jsondata.isEmpty()){
            JsonObject o = new JsonParser().parse(jsondata).getAsJsonObject();
            System.out.println(o.toString());
            registrationNumber = o.getAsJsonObject().get("regNo").getAsString();
            ip = o.getAsJsonObject().get("Ip").getAsString();
            userId = o.getAsJsonObject().get("userId").getAsString();
        }
        VahanObject data = null;
        try {
            VahanObject object = new VahanObject();

            object.setFunction_name(Constants.getCarDetailsVahan);
            object.setUrl(Constants.vahan);
            object.setParameters_to_send(registrationNumber);
             data = http.postData(object);
            if (data != null && data.getSuccessFail().equalsIgnoreCase("SUCCESS")) {
                VehicleDetailsObject objectVehicle = ParseXML.parseXml(data.getResponse());
             VahanLog log =   createLog(data,ip,userId,objectVehicle);
             vahanLogsRepository.save(log);
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, objectVehicle);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                VehicleDetailsObject objectVehicle = null;
                VahanLog log =   createLog(data,ip,userId,objectVehicle);
                vahanLogsRepository.save(log);
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, data.getResponse());
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            VehicleDetailsObject objectVehicle = null;
            VahanLog log =   createLog(data,ip,userId,objectVehicle);
            vahanLogsRepository.save(log);
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    /**
     * Saarthi Service
     */
    @RequestMapping(value = "/api/getDlDetails/{dl}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getDLDetails(@PathVariable("dl") String dl) {
        Map<String, Object> map = null;
        try {
            HTTP http = new HTTP();
            SaarthiObject object = http.getSarthiData(dl);
            if (!Utilities.positiveNegitive(object.getErrorCode())) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, object);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, object);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



    /**
     *
     */
    @RequestMapping(value = "/api/barriers/{districtId}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getBarriers(@PathVariable("districtId") int districtId) {
        System.out.println(districtId);
        Map<String, Object> map = null;
        try {
            List<BarrierMaster> barriers = barrierService.getBarriers(districtId);
            if (!barriers.isEmpty()) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, barriers);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, barriers);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return
     */
    @RequestMapping(value = "/api/vehicletypes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getVehicleTypes() {

        Map<String, Object> map = null;
        try {
            List<VehicleTypeMaster> vehicleType = vehicleTypeService.getVehicleTypes();
            if (!vehicleType.isEmpty()) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, vehicleType);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, vehicleType);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * @return
     */
    @RequestMapping(value = "/api/vehicleusertypes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> getVehicleUserTypes() {

        Map<String, Object> map = null;
        try {
            List<VehicleUserType> vehicleUserType = vehicleUserTypeService.getVehicleUserTypes();
            if (!vehicleUserType.isEmpty()) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, vehicleUserType);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, vehicleUserType);
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/api/getotp/{mobile}", method = RequestMethod.GET, produces = "application/json")
    //@Async("threadPoolTaskExecutor")
    @Transactional
    public ResponseEntity<?> getOTP(@PathVariable("mobile") String mobile) {

        Map<String, Object> map = null;
        try {
            String SMSServerCode = null;
            Long mobileNumber = Long.valueOf(mobile);
            SMSServices smsService = new SMSServices();
            String optToSend = random24.randomDecimalString(6);
            String otpMessage = Utilities.createOtpMessage(optToSend);
            String sendOTP = smsService.sendOtpSMS(Constants.smsUsername, Constants.smsPassword, otpMessage, Constants.smsSenderId, Long.toString(mobileNumber), Constants.smsSecureKey);
            if (!sendOTP.isEmpty()) {

                SMSServerCode = sendOTP.split(",")[0];


                try {
                    if (SMSServerCode.equalsIgnoreCase("402")) {
                        OTPMaster otpentity = new OTPMaster();
                        otpentity.setMobilenumber(mobileNumber);
                        otpentity.setOtp(Integer.parseInt(optToSend));
                        otpentity.setActive(true);

                        //Update Table if Required
                        if (!otpService.isRecordExist(otpentity)) {
                            otpService.saveOPT(otpentity);
                        } else {
                            otpService.updateOTPTable(otpentity);
                            otpService.saveOPT(otpentity);
                        }
                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, "OTP sent Successfully to the Registered Mobile number.");
                        map.put(Constants.keyMessage, Constants.valueMessage);
                        map.put(Constants.keyStatus, HttpStatus.OK);
                        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);

                    } else {
                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, "Unable to send OTP .Please connect to Internet and try again.");
                        map.put(Constants.keyMessage, Constants.valueMessage);
                        map.put(Constants.keyStatus, HttpStatus.OK);
                        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
                    }


                } catch (Exception ex) {

                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, ex.getLocalizedMessage());
                    map.put(Constants.keyMessage, Constants.valueMessage);
                    map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
                    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);

                }


            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "Something went wrong. Please connect to Internet and try again.");
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @SuppressWarnings("unused")
    @RequestMapping(value = "/api/verifyotp/{mobile}/{otp}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<?> verifyOtp(@PathVariable("mobile") String mobile, @PathVariable("otp") String otp) {
        Map<String, Object> map = null;
        UserEntity user = null;
        UsePoJo userToSend = new UsePoJo();
        try {
            if (mobile != null && !mobile.isEmpty() && otp != null && !otp.isEmpty()) {
                Long mobileNumber = Long.valueOf(mobile);
                Integer otpNumber = Integer.parseInt(otp);

                //Check Weather The Otp and Number Matches
                if (otpService.VerifyOtp(mobileNumber, otpNumber)) {
                    System.out.println("OTP and Mobile Number Match");
                    //GET USER ID ON THE BASIS OF Mobile Number
                    user = userService.getUserDetails(mobileNumber);
                    System.out.println("!@!@!@#$#$%^&" + user.getUserId());
                    Long iduser = user.getUserId();
                    String userName = user.getUserName();
                    userToSend.setUser_id(iduser);
                    userToSend.setUser_name(userName);
                    userToSend.setMobile_number(user.getMobileNumber());


                    if (userToSend != null) {
                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, userToSend);
                        map.put(Constants.keyMessage, Constants.valueMessage);
                        map.put(Constants.keyStatus, HttpStatus.OK);
                        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
                    } else {
                        map = new HashMap<String, Object>();
                        map.put(Constants.keyResponse, "Unable to find User Details with the specific Mobile Number");
                        map.put(Constants.keyMessage, Constants.valueMessage);
                        map.put(Constants.keyStatus, HttpStatus.OK);
                        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
                    }


                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "Authentication Failed. OTP and Mobile number mismatch.");
                    map.put(Constants.keyMessage, Constants.valueMessage);
                    map.put(Constants.keyStatus, HttpStatus.OK);
                    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
                }
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "");
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }


    @RequestMapping(value = "/api/generateqrcode/{id}", method = RequestMethod.GET)
    //produces = MediaType.APPLICATION_PDF_VALUE
    public ResponseEntity<?> generateQrcode(@PathVariable("id") String id) throws IOException, WriterException, DocumentException {
        Map<String, Object> map = null;
        try {

            String Id = Utilities.base64Decode(id);
            System.out.println("Decoded Value" + Id);

            Optional<VehicleOwnerEntries> vehicleOwnerEntries = entriesService.getOwnerDetails(Long.valueOf(Id));

            if (vehicleOwnerEntries.get() != null) {
                ByteArrayInputStream bis = GeneratePdfReport.generateIdCard(vehicleOwnerEntries.get());


                HttpHeaders headers = new HttpHeaders();
                headers.add("Content-Disposition", "inline; filename=" + vehicleOwnerEntries.get().getIdCardNumber() + ".pdf");


                return ResponseEntity
                        .ok()
                        .headers(headers)
                        .contentType(MediaType.APPLICATION_PDF)
                        .body(new InputStreamResource(bis));
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "Unable to find User Details with the specific Mobile Number");
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }
        } catch (Exception ex) {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "");
            map.put(Constants.keyMessage, ex.getLocalizedMessage().toString());
            map.put(Constants.keyStatus, HttpStatus.INTERNAL_SERVER_ERROR);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
        }


    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/verifyVehicle", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> verifyvehicle(@RequestBody String jsondata) throws IOException {

        VehicleOwnerEntries vehicleUSerEntries = null;
        Map<String, Object> map = null;
        System.out.println(jsondata);
        if (jsondata != null) {
            JsonElement jelement = new JsonParser().parse(jsondata);
            JsonObject jobject = jelement.getAsJsonObject();
            String vehicle_number = jobject.get("vehicle_number").getAsString();
            Long mobile_number = jobject.get("mobile_number").getAsLong();
            String id_card_number = jobject.get("id_card_number").getAsString();


            vehicleUSerEntries = entriesService.scanQrCodeDate(mobile_number, id_card_number, vehicle_number);
            if (vehicleUSerEntries != null) {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/downloadFile/")
                        .path(vehicleUSerEntries.getVehicleOwnerImageName())
                        .toUriString();


                //Save Vehicle Entries

                String generatePdfUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/generateqrcode/")
                        .path(Utilities.base64Encode(Long.toString(vehicleUSerEntries.getVehicleOwnerId())))
                        .toUriString();


                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, new UploadFileResponse("", fileDownloadUri, generatePdfUrl, "", 0, vehicleUSerEntries));
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "No Data Found");
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Invalid Data");
            map.put(Constants.keyMessage, Constants.valueMessage);
            map.put(Constants.keyStatus, HttpStatus.OK);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    @RequestMapping(method = RequestMethod.POST, value = "/api/saveVehicleData", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> saveVehicleDetails(@RequestBody String jsondata) throws IOException {

        VehicleInOutTrans vehicleInOutEntitity = null;
        Map<String, Object> map = null;
        System.out.println(jsondata);
        if (jsondata != null) {
            vehicleInOutEntitity = objectMapper.readValue(jsondata, VehicleInOutTrans.class);

            try {
                vehicleInOutEntitity = vehicleInOutService.saveVehicleInOutTrans(vehicleInOutEntitity);
                if (vehicleInOutEntitity != null) {

                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "Successfully Saved");
                    map.put(Constants.keyMessage, Constants.valueMessage);
                    map.put(Constants.keyStatus, HttpStatus.OK);
                    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
                } else {
                    map = new HashMap<String, Object>();
                    map.put(Constants.keyResponse, "Unable to save the Data.");
                    map.put(Constants.keyMessage, Constants.valueMessage);
                    map.put(Constants.keyStatus, HttpStatus.OK);
                    return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
                }
            } catch (Exception ex) {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, ex.getLocalizedMessage().toString());
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNPROCESSABLE_ENTITY);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Invalid Data");
            map.put(Constants.keyMessage, Constants.valueMessage);
            map.put(Constants.keyStatus, HttpStatus.OK);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    //searchId
    @RequestMapping(method = RequestMethod.POST, value = "/api/searchId", consumes = "application/json", produces = "application/json")
    @ResponseBody
    public ResponseEntity<?> searchIdCard(@RequestBody String jsondata) throws IOException {

        VehicleOwnerEntries vehicleUSerEntries = null;
        Map<String, Object> map = null;
        System.out.println(jsondata);
        if (jsondata != null) {
            JsonElement jelement = new JsonParser().parse(jsondata);
            JsonObject jobject = jelement.getAsJsonObject();
            String vehicle_number = jobject.get("vehicle_number").getAsString();
            Long mobile_number = jobject.get("mobile_number").getAsLong();


            vehicleUSerEntries = entriesService.searchIdentity(mobile_number, vehicle_number);
            if (vehicleUSerEntries != null) {
                String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/downloadFile/")
                        .path(vehicleUSerEntries.getVehicleOwnerImageName())
                        .toUriString();


                //Save Vehicle Entries

                String generatePdfUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                        .path("/api/generateqrcode/")
                        .path(Utilities.base64Encode(Long.toString(vehicleUSerEntries.getVehicleOwnerId())))
                        .toUriString();


                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, new UploadFileResponse("", fileDownloadUri, generatePdfUrl, "", 0, vehicleUSerEntries));
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            } else {
                map = new HashMap<String, Object>();
                map.put(Constants.keyResponse, "No Data Found");
                map.put(Constants.keyMessage, Constants.valueMessage);
                map.put(Constants.keyStatus, HttpStatus.OK);
                return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
            }


        } else {
            map = new HashMap<String, Object>();
            map.put(Constants.keyResponse, "Invalid Data");
            map.put(Constants.keyMessage, Constants.valueMessage);
            map.put(Constants.keyStatus, HttpStatus.OK);
            return new ResponseEntity<Map<String, Object>>(map, HttpStatus.UNPROCESSABLE_ENTITY);
        }

    }

    private VahanLog createLog(VahanObject data, String clientIp, String userID,  VehicleDetailsObject vObject) {

        VahanLog Log = new VahanLog();
        Log.setLogApplicationName("HP Transport ID");
        Log.setLogFunctionName(data.getFunction_name());
        Log.setLogIpAddress(clientIp);
        if(data.getSuccessFail().equalsIgnoreCase("FALIURE")){
            Log.setLogServiceResponseCode(404);
        }else{
            Log.setLogServiceResponseCode(200);
        }
        if(vObject!=null){
            Log.setEngineNumber(vObject.getRcEngineNumber());
            Log.setChassisNumber(vObject.getRcChassisNo());
            Log.setRcStatus(vObject.getRcStatus());
            Log.setRcRegisteredAt(vObject.getRcRegisteredAt());
            Log.setRegNo(vObject.getRcRegistrationNo());
            Log.setRcFitUpto(vObject.getRcFitUpto());
            Log.setRcStatusAsOnDate(vObject.getRcStatusAsOn());
            Log.setRcOwnerName(vObject.getRcOwnerName());
        }else{
            Log.setEngineNumber("");
            Log.setChassisNumber("");
            Log.setRcStatus("");
            Log.setRcRegisteredAt("");
            Log.setRegNo("");
            Log.setRcFitUpto("");
            Log.setRcStatusAsOnDate("");
            Log.setRcOwnerName("");
        }


        Log.setLogUserId(Long.valueOf(userID));
        Log.setActive(true);
        Log.setLogVehicleNumber(data.getParameters_to_send());



        return Log;
    }

}
