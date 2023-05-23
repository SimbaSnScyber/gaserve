package za.co.gaserve.api.auth.Filters;

        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.web.servlet.FilterRegistrationBean;
        import org.springframework.context.annotation.Bean;
        import org.springframework.context.annotation.Configuration;

        import java.util.Collections;

@Configuration
public class Filters {

  private final LoginFilter loginFilter;
  private final GaserveFilter gaserveFilter;

  @Autowired
  public Filters(LoginFilter loginFilter, GaserveFilter gaserveFilter) {
    this.loginFilter = loginFilter;
    this.gaserveFilter = gaserveFilter;
  }

  @Bean
  public FilterRegistrationBean loginRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(loginFilter);
    filterRegistrationBean.setUrlPatterns(Collections.singletonList("/login/*"));
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean restRegistrationBean() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(gaserveFilter);
    filterRegistrationBean.setUrlPatterns(Collections.singletonList("/rest"));
    return filterRegistrationBean;
  }
}
