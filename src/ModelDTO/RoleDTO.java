package ModelDTO;

public class RoleDTO {
    private Integer id ;        //role un id si
    private String name ;       //rolun name i yeni admin ,user ve.s

    public RoleDTO(Integer id, String name) {
        this.id = id;
        this.name = name;
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
}
