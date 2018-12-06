class Contacts{
    private String firstName;
    private String lastName;
    private String contactNo;

    public Contacts( String firstN , String lastN, String number ) {
        firstName = firstN;
        lastName = lastN;
        contactNo = number;
    }

    public String getFirstName(){
        return firstName;
    }
    
    public String getLastName(){
        return lastName;
    }
    
    public String getNumber(){
        return contactNo;
    }

}