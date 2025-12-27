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

    All of these behind there is Entity Manager :
    --- we create object in heap memory it is called Transient state
    --- when we call save(), persist(), update() methods then it is called Persistent state
    --- when we call get(), load() also then still it is in Persistent state
    --- but when we call remove() or delete() method then it is called Removed State
    --- also we call detach(), close(), clear() then it went to Detach() State but in this we can go again in Persistent state
        by calling save(), merge() , lock() we again go to Persistent() state but in other we can't go to



    If we call  persist() method on repository then we are in persistent context so we call same thing again nd again after making two
    different object hibernate run the query every time so to overcome this we have to annotate our method with @Transactional
    annotation , then we are in transactional context and now if we call same query hibernate run only 1 time

    also,,,,,
    let make a findById call..
    ...first check data is in persistence context or not if there then fetch else call a select call
    ... then store and return
    ... but after then if you set or remove any attribute from entity then also it perform operation if it is in
    ...Transaction context


    ------------------ Sorting

    for sorting we can pass fields in parameter using request param or not in controller as per demand
    then in service class we can use sort class to fetch field name and their direction to sort
    if not given by user then also we can add as our need for default sort

    Sort.Direction direction1 = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction1,sortBy)
                .and(Sort.by("email"))
                .and(Sort.by(Sort.Direction.DESC, "age"));


     -------------------- Pagination

     Sort.Direction direction1 = direction.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Sort sort = Sort.by(direction1,sortBy)
                .and(Sort.by("email"))
                .and(Sort.by(Sort.Direction.DESC, "age"));

        Pageable pageable = PageRequest.of(pageNumber,PAGE_SIZE,sort);

        List<EmployeeEntity> employeeEntities = employeeRepository.findAll(pageable).getContent();


        in this way we can achieve pagination



        ------------------------- Auditing


        1. create auditable base entity using @EntityListeners(AuditingEntityListeners.class)
        2. By above we get access for @CreatedBy, @CreatedDate, @LastModifiedBy and @LastModifiedDate
        3. Extend the above class to all entities
        4. Add @EnableJPAAuditing in configuration class -- till now our created and modified date will come
        5. To get the user, We need to create class which implements AuditorAware Interface to provide current authenticated user
            from spring security
        6. If we do not want to use above configuration then we need to use these three annotations and do write our service or business
           logic in it
              * @PrePersist  **@PreUpdate ***@PreRemove
              these annotations we can use in Entity class also

     */
}
