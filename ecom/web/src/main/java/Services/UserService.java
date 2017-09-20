package Services;

public class UserService {
    public static Boolean samePaswword(String firstPassword, String secondPasword){
        return firstPassword.equals(secondPasword);
    }

}
