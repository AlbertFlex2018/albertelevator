package elevator.admin;

public class Admin {
    
    public final long id;
    private AdminGrunt grunt;
    private String password;

    public Admin(long id,String password,AdminGrunt grunt) {
        this.id=id;
        this.grunt = grunt;
        this.password = password;
    }
        
    public AdminGrunt getGrunt() {
        return grunt;
    }

    public void setGrunt(AdminGrunt grunt) {
        this.grunt = grunt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }    
}
