package ModelDTO;

public class DictionaryDTO {

    private Integer id ;
    private String dicVal;

    public DictionaryDTO(Integer id, String dicVal) {
        this.id = id;
        this.dicVal = dicVal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDicVal() {
        return dicVal;
    }

    public void setDicVal(String dicVal) {
        this.dicVal = dicVal;
    }

    @Override
    public String toString() {
        return "DictionaryDTO{" +
                "id=" + id +
                ", dicVal='" + dicVal + '\'' +
                '}';
    }
}
