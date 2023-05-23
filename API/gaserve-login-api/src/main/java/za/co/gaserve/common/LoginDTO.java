package za.co.gaserve.common;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

/**
 * LoginDTO
 */


public class LoginDTO {

  @JsonProperty("ip")
  private String ip = null;

  @JsonProperty("mac")
  private String mac = null;

  @JsonProperty("deviceType")
  private String deviceType = null;

  @JsonProperty("email")
  private String email = null;

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public LoginDTO email(String email) {
    this.email = email;
    return this;
  }




  /**
   * Get ip
   * @return ip
  **/


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }

  public LoginDTO ip(String ip) {
    this.ip = ip;
    return this;
  }

  /**
   * Get mac
   * @return mac
  **/

  public String getMac() {
    return mac;
  }

  public void setMac(String mac) {
    this.mac = mac;
  }

  public LoginDTO deviceType(String deviceType) {
    this.deviceType = deviceType;
    return this;
  }

  /**
   * Get deviceType
   * @return deviceType
  **/



  public String getDeviceType() {
    return deviceType;
  }

  public void setDeviceType(String deviceType) {
    this.deviceType = deviceType;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    LoginDTO loginDTO = (LoginDTO) o;
    return
        Objects.equals(this.ip, loginDTO.ip) &&
        Objects.equals(this.mac, loginDTO.mac) &&
        Objects.equals(this.deviceType, loginDTO.deviceType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(ip, mac, deviceType);
  }

  @Override
  public String toString() {
    return "LoginDTO{" +
            "ip='" + ip + '\'' +
            ", mac='" + mac + '\'' +
            ", deviceType='" + deviceType + '\'' +
            ", email='" + email + '\'' +
            '}';
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }


}

