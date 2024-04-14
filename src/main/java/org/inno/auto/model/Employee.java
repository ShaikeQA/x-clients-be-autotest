package org.inno.auto.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class Employee {

    Integer id;
    Boolean isActive = null;
    String createDateTime;
    String lastChangedDateTime;
    String firstName;
    String lastName;
    String middleName;
    String phone;
    String email;
    String birthdate;
    String avatar_url;
    Integer companyId;
    String url;

}
