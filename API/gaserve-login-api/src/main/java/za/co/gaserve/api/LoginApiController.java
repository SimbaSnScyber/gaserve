package za.co.gaserve.api;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import za.co.gaserve.common.ErrorDTO;
import za.co.gaserve.common.LoginDTO;
import za.co.gaserve.common.UserDTO;
import za.co.gaserve.entities.BusinessException;
import za.co.gaserve.entities.user.Login;
import za.co.gaserve.entities.user.User;
import co.za.gaserve.business.UserBusinessService;

import java.security.PrivateKey;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2018-09-22T18:20:11.403Z")

@RestController
public class LoginApiController {

    private static final Logger log = LoggerFactory.getLogger(LoginApiController.class);

    @Autowired
    private UserBusinessService businessService;

    @PostMapping("/login")
    public ResponseEntity authoriseUser(@Valid @RequestBody LoginDTO loginAttempt) {

        User byEmail = null;

        try{
            Login login = new Login();

             byEmail = businessService.findByEmail(loginAttempt.getEmail());

            login.setUser(byEmail);
            login.setMac(loginAttempt.getMac());
            login.setIp(loginAttempt.getIp());

            businessService.login(login);

            UserDTO userDTO = new UserDTO(byEmail);
            userDTO.setUserLocked(false);
            userDTO.setStockTakingDone(true);
            userDTO.setId(byEmail.getId());
            userDTO.setUserStatus(byEmail.getStatus().getId());

            return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
        }
        catch (BusinessException e){
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage(e.getMessage());
            errorDTO.setCode(e.getStatusCode().value());

            if (byEmail == null){
                errorDTO.setUserStatus(null);
                errorDTO.setUserId(null);
            }else{
                errorDTO.setUserStatus(byEmail.getStatus().getId());
                errorDTO.setUserId(byEmail.getId());
            }

            return new ResponseEntity(errorDTO, e.getStatusCode());
        }
        catch (Exception e){
            ErrorDTO errorDTO = new ErrorDTO();
            errorDTO.setMessage(e.getMessage());
            errorDTO.setCode(500);
            log.error("Unexpected Exception",e);
            return new ResponseEntity(errorDTO,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
