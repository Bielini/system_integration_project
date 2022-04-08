package pl.bie.api.soap;
import jakarta.jws.WebService;

import javax.jws.WebMethod;

import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface SOAPInterface {

    @WebMethod
    String getAllByName(String name);

    @WebMethod
    String getSpecificCategoryData(String name);

    @WebMethod
    String getSpecificYearData(String year);


}
