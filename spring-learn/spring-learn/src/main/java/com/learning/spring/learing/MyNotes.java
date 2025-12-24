package com.learning.spring.learing;

public class MyNotes {


    /*this is for understanding

    ------------------------- request param
    @GetMapping
    public String getAll(@RequestParam(required = false, name = "inputAge") Integer age , here in url inputAge should be given
                              @RequestParam(required = false) String sortBy){
        return ("age : " + age + " sortBy: " + sortBy);
    }

    path variable -- parameter is essential for us
    request param -- optional parameter

    ------------------------- more validations
    @NotNull -- check null or not
    @NotBlank -- check null or not and size > zero
    @NotEmpty -- check null or not and trimmed size > 0
    @Pattern -- check regular expression
    @Positive or @PositiveOrZero -- check null should be >= 0
    @Negative or @NegativeOrZero -- check null should be <= 0
    @Past -- check date should be of past
    @Future or @FutureOrPresent -- check date should be of present or of future
    @AssertTrue -- check boolean is true or not
    @AssertFalse -- check boolean is false or not

     ------------------------------ to create new validation
    we have to create new @Interface add target and retention policy then few methods like message ,payload
    then create new class which implements ConstraintValidator<EmployeeRoleValidation,String>
    in which we need to our created interface and field type
    then on field we need to give @EmployeeRoleValidation -- interface name on validated field


    ---------------------------------- JPA/hibernate

    JPA -- is a specification as how object and relations should take place
    hibernate -- is a implementation of JPA specification

    normally we do not need to write any specific queries just give correct format
    but if we need to write incase
    use @Query -----> but in this we need to give table name same as java entity and everything is case-sensitive

     */
}
