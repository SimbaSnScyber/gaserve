/**
 * NOTE: This class is auto generated by the swagger code generator program (2.3.1).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.ErrorDTO;
import io.swagger.model.SalesReportDTO;
import io.swagger.annotations.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-12T18:18:01.135Z")

@Api(value = "reports", description = "the reports API")
public interface ReportsApi {

    @ApiOperation(value = "searches for a report", nickname = "searchReport", notes = "By passing in the appropriate options, you can search for reports", response = SalesReportDTO.class, tags={ "Reports", })
    @ApiResponses(value = { 
        @ApiResponse(code = 200, message = "search results matching criteria", response = SalesReportDTO.class),
        @ApiResponse(code = 400, message = "bad input parameter", response = ErrorDTO.class),
        @ApiResponse(code = 500, message = "Unexpected error", response = ErrorDTO.class) })
    @RequestMapping(value = "/reports",
        produces = { "application/json" }, 
        method = RequestMethod.GET)
    ResponseEntity<?> searchReport(String userId);

}
