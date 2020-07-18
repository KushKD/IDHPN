package com.dit.himachal.controller;

import com.dit.himachal.entities.VehicleOwnerEntries;
import com.dit.himachal.form.ReportsForm;
import com.dit.himachal.services.VehicleOwnerEntriesService;
import com.dit.himachal.utilities.ExcelFileExporter;
import com.dit.himachal.utilities.Utilities;
import com.dit.himachal.validators.GenerateReportsValidator;
import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ReportsController {

    @Autowired
    GenerateReportsValidator generateReportsValidator;

    @Autowired
    VehicleOwnerEntriesService vehicleOwnerEntriesService;

    @RequestMapping(value = "/generateReport", method = RequestMethod.GET)
    public String saveRole(Model model) {
        model.addAttribute("reportsForm", new ReportsForm());
        return "reports";
    }

    @RequestMapping(value = "/getReports", method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.OK)
    public void  getIdCardList(@ModelAttribute("reportsForm") ReportsForm reportsForm,
                                       BindingResult bindingResult, Model model, HttpServletResponse response, HttpServletRequest request) {
        generateReportsValidator.validate(reportsForm, bindingResult);

//        if (bindingResult.hasErrors()) {
//            return new ModelAndView("reports");
//        }
        try {
            if (Utilities.ifEmptyField(reportsForm.getDistrictId()) && Utilities.ifEmptyField(reportsForm.getBarrierId())) {
                System.out.println(reportsForm.getDistrictId());
                System.out.println(reportsForm.getBarrierId());
                List<VehicleOwnerEntries> dataForReports = null;

                /**
                 * If All the Parameters are Present
                 * DistrictId, BarrierId, VehicleType, Owner Type, From Date , To Date
                 */
                if (Utilities.ifEmptyField(reportsForm.getVehicleType()) &&
                        Utilities.ifEmptyField(reportsForm.getOwnerType()) &&
                        Utilities.ifEmptyField(reportsForm.getFromDate()) &&
                        Utilities.ifEmptyField(reportsForm.getToDate())) {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportAllParameters(Integer.parseInt(reportsForm.getDistrictId()),
                            Integer.parseInt(reportsForm.getBarrierId()), Integer.parseInt(reportsForm.getVehicleType()),
                            Integer.parseInt(reportsForm.getOwnerType()), reportsForm.getFromDate(), reportsForm.getToDate());
                }

                /**
                 * For the following Parameters
                 * DistrictId, BarrierId, VehicleType, FromDate, toDate
                 */
                else if (Utilities.ifEmptyField(reportsForm.getVehicleType()) &&
                        !Utilities.ifEmptyField(reportsForm.getOwnerType()) &&
                        Utilities.ifEmptyField(reportsForm.getFromDate()) &&
                        Utilities.ifEmptyField(reportsForm.getToDate())) {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportAllParametersNoOwner(Integer.parseInt(reportsForm.getDistrictId()),
                            Integer.parseInt(reportsForm.getBarrierId()), Integer.parseInt(reportsForm.getVehicleType()),
                            reportsForm.getFromDate(), reportsForm.getToDate());
                }

                /**
                 * For the following Parameters
                 * DistrictId, BarrierId, OwnerType, FromDate, toDate
                 */
                else if (!Utilities.ifEmptyField(reportsForm.getVehicleType()) &&
                        Utilities.ifEmptyField(reportsForm.getOwnerType()) &&
                        Utilities.ifEmptyField(reportsForm.getFromDate()) &&
                        Utilities.ifEmptyField(reportsForm.getToDate())) {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportAllParametersOwnerDates(Integer.parseInt(reportsForm.getDistrictId()),
                            Integer.parseInt(reportsForm.getBarrierId()), Integer.parseInt(reportsForm.getOwnerType()),
                            reportsForm.getFromDate(), reportsForm.getToDate());
                }

                /**
                 * For the following Parameters
                 * DistrictId, BarrierId, FromDate, toDate
                 */
                else if (!Utilities.ifEmptyField(reportsForm.getVehicleType()) &&
                        !Utilities.ifEmptyField(reportsForm.getOwnerType()) &&
                        Utilities.ifEmptyField(reportsForm.getFromDate()) &&
                        Utilities.ifEmptyField(reportsForm.getToDate())) {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportDistrictBarrierToFrom(Integer.parseInt(reportsForm.getDistrictId()),
                            Integer.parseInt(reportsForm.getBarrierId()), reportsForm.getFromDate(), reportsForm.getToDate());
                }

                /**
                 * For the following Parameters
                 * DistrictId, BarrierId, FromDate
                 */
                else if (Utilities.ifEmptyField(reportsForm.getFromDate()) &&
                        !Utilities.ifEmptyField(reportsForm.getToDate()) &&
                        !Utilities.ifEmptyField(reportsForm.getVehicleType()) &&
                        !Utilities.ifEmptyField(reportsForm.getOwnerType())) {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportDistrictBarrierFrom(Integer.parseInt(reportsForm.getDistrictId()),
                            Integer.parseInt(reportsForm.getBarrierId()), reportsForm.getFromDate());
                }


                /**
                 * For the following Parameters
                 * DistrictId, BarrierId, VehicleType
                 */
                else if (!Utilities.ifEmptyField(reportsForm.getFromDate()) &&
                        !Utilities.ifEmptyField(reportsForm.getToDate()) &&
                        Utilities.ifEmptyField(reportsForm.getVehicleType()) &&
                        !Utilities.ifEmptyField(reportsForm.getOwnerType())) {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportDistrictBarrierVehicleType(Integer.parseInt(reportsForm.getDistrictId()), Integer.parseInt(reportsForm.getBarrierId()), Integer.parseInt(reportsForm.getVehicleType()));
                }

                /**
                 * For the following Parameters
                 * DistrictId, BarrierId, Owner Type
                 */
                else if (!Utilities.ifEmptyField(reportsForm.getFromDate()) &&
                        !Utilities.ifEmptyField(reportsForm.getToDate()) &&
                        Utilities.ifEmptyField(reportsForm.getOwnerType()) &&
                        !Utilities.ifEmptyField(reportsForm.getVehicleType())) {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportDistrictBarrierOwnerTypeType(Integer.parseInt(reportsForm.getDistrictId()), Integer.parseInt(reportsForm.getBarrierId()), Integer.parseInt(reportsForm.getOwnerType()));
                }

                /**
                 * For the following Parameters
                 * DistrictId, BarrierId, Owner Type, Vehicle Type
                 */
                else if (!Utilities.ifEmptyField(reportsForm.getFromDate()) &&
                        !Utilities.ifEmptyField(reportsForm.getToDate()) &&
                        Utilities.ifEmptyField(reportsForm.getOwnerType()) &&
                        Utilities.ifEmptyField(reportsForm.getVehicleType())) {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportDistrictBarrierOwnerTypeVehicleType(Integer.parseInt(reportsForm.getDistrictId()), Integer.parseInt(reportsForm.getBarrierId()), Integer.parseInt(reportsForm.getVehicleType()), Integer.parseInt(reportsForm.getOwnerType()));
                }

                /**
                 * For the following Parameters
                 * DistrictId, BarrierId
                 */
                else {
                    dataForReports = new ArrayList<>();
                    dataForReports = vehicleOwnerEntriesService.getReportDistrictBarrier(Integer.parseInt(reportsForm.getDistrictId()), Integer.parseInt(reportsForm.getBarrierId()));
                }


                if (!dataForReports.isEmpty()) {
                    request.getSession().setAttribute("successMessage", "Data found Successfully");
                    //  model.addAttribute("vehicledata", data);
                    model.addAttribute("barrierid", reportsForm.getBarrierId());
                    model.addAttribute("districtid", reportsForm.getDistrictId());
                    model.addAttribute("vid", reportsForm.getVehicleType());
                    model.addAttribute("oid", reportsForm.getOwnerType());

                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment; filename=Report_id_card.xlsx");
                    ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(dataForReports);
                    IOUtils.copy(stream, response.getOutputStream());
                    response.flushBuffer();
                    //ExcelView view = new ExcelView(dataForReports);
                   // return new ModelAndView("reports");
                   // return new ModelAndView(new ExcelView(),"data", dataForReports);
                } else {
                    model.addAttribute("barrierid", reportsForm.getBarrierId());
                    model.addAttribute("districtid", reportsForm.getDistrictId());
                    model.addAttribute("vid", reportsForm.getVehicleType());
                    model.addAttribute("oid", reportsForm.getOwnerType());

                   // model.addAttribute("serverError", "No Data available for the current District and Barrier");
                  //  return new ModelAndView("reports");
                    response.setContentType("application/octet-stream");
                    response.setHeader("Content-Disposition", "attachment; filename=Report_id_card.xlsx");
                    ByteArrayInputStream stream = ExcelFileExporter.contactListToExcelFile(dataForReports);
                    IOUtils.copy(stream, response.getOutputStream());
                    response.flushBuffer();
                }
            } else {
                model.addAttribute("serverError", "No Select  District and Barrier");

               // return new ModelAndView("reports");
            }


        } catch (Exception ex) {
            reportsForm.setFromDate("");
            reportsForm.setToDate("");
            model.addAttribute("serverError", ex.toString());
           // return new ModelAndView("reports");
        }

    }

}
