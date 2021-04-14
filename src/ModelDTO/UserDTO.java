package ModelDTO;

import java.util.List;

public class UserDTO {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String pin;
    private String personLabel;
    private String password;
    private Integer personType;
    private String imageUrl;


    private List<RoleDTO> roles;


    public UserDTO() {
    }

    public UserDTO(Integer id,String name, String surname, String email, String pin, Integer personType) {
        this.id=id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pin = pin;
        this.personType = personType;
    }
    public UserDTO(Integer id, String name, String surname, String email, String pin, String personLabel) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pin = pin;
        this.personLabel = personLabel;
    }
    public UserDTO(Integer id, String name, String surname, String email, String pin, String personLabel,Integer personType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.pin = pin;
        this.personLabel = personLabel;
        this.personType = personType;
    }
    public UserDTO( String name, String surname, String pin, String email,String password, Integer personType) {
        this.name = name;
        this.surname = surname;
        this.pin = pin;
        this.email = email;
        this.personType = personType;
        this.password=password;
    }

    public UserDTO(Integer id, String name, String surname, String pin) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.pin = pin;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", pin='" + pin + '\'' +
                ", personLabel='" + personLabel + '\'' +
                ", password='" + password + '\'' +
                ", personType=" + personType +
                ", roles=" + roles +
                '}';
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<RoleDTO> getRoles() {
        return roles;
    }

    public void setRoles(List<RoleDTO> roles) {
        this.roles = roles;
    }

    public Integer getPersonType() {
        return personType;
    }

    public void setPersonType(Integer personType) {
        this.personType = personType;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonLabel() {
        return personLabel;
    }

    public void setPersonLabel(String personLabel) {
        this.personLabel = personLabel;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
