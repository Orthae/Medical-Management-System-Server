package orthae.com.github.medicalmanagementsystem.core;

@SuppressWarnings("unused")
public interface Employee  {
    int getId();
    String getName();
    String getSurname();
    String getUsername();
    String getPassword();
    void setId(int id);
    void setName(String name);
    void setSurname(String surname);
    void setUsername(String username);
    void setPassword(String password);

}
