package io.swagger.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import za.co.gaserve.entities.user.User;

import java.util.List;

public class StockTakingDoneDTO {

   @JsonProperty("user")
   private User user;

   @JsonProperty("errorMessage")
   private List<String> errorMessage;

   public User getUser() {
       return user;
   }

   public void setUser(User user) {
       this.user = user;
   }


    public List<String> getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(List<String> errorMessage) {
        this.errorMessage = errorMessage;
    }
}
