import java.util.Date;
import java.text.SimpleDateFormat;

public class Personagem {
    private String id;
    private String name;
    private String alternateNames; // Assuming alternate names are strings
    private String house;
    private String species;
    private String patronus;
    private boolean hogwartsStaff; // Transformado em String
    private boolean hogwartsStudent; // Transformado em String
    private String actorName;
    private boolean alive; // Transformado em String
    private Date birthDate;
    private String birthDateString;
    public String getBirthDateString() {
        return birthDateString;
    }

    public void setBirthDateString(String birthDateString) {
        this.birthDateString = birthDateString;
    }
    private int yearOfBirth;
    private String eyeColour;
    private String gender;
    private boolean wizard; // Transformado em String
    private String ancestry; // Adicionado novo atributo
    private String alternateActors; // Transform
    private String hairColour; // Transform

    public String getHairColour() {
        return hairColour;
    }

    public void setHairColour(String hairColour) {
        this.hairColour = hairColour;
    }

    public String getAlternateActors() {
        return alternateActors;
    }

    public void setAlternateActors(String alternateActors) {
        this.alternateActors = alternateActors;
    }

    public Personagem(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Personagem() {
        this.id = "0";
        this.name = "";
        this.species = "";
    }
    SimpleDateFormat formatoEntrada = new SimpleDateFormat("yyyy-MM-dd");
    public void imprimir() {
        System.out.print("[" + id + " ## " + name + " ## " + alternateNames + " ## " + house +" ## " + ancestry + " ## " + species + " ## " + patronus + " ## " +
                hogwartsStaff + " ## " + hogwartsStudent + " ## " + actorName + " ## " + alive + " ## " + birthDateString +
                " ## " + yearOfBirth + " ## " + eyeColour + " ## " + gender + " ## " + hairColour +" ## " + wizard +  ']');
    }

    // Métodos getters e setters omitidos para brevidade

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAlternateNames() {
        return alternateNames;
    }

    public void setAlternateNames(String alternateNames) {
        this.alternateNames = alternateNames;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getPatronus() {
        return patronus;
    }

    public void setPatronus(String patronus) {
        this.patronus = patronus;
    }

    public Boolean getHogwartsStaff() {
        return hogwartsStaff;
    }

    public void setHogwartsStaff(String dado) {
        if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
            this.hogwartsStaff = false;
        } else {
            this.hogwartsStaff = false;
        }
    }

    public Boolean getHogwartsStudent() {
        return hogwartsStudent;
    }

    public void setHogwartsStudent(String dado) {
        if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
            this.hogwartsStudent = false;
        } else {
            this.hogwartsStudent = false;
        }
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public boolean getAlive() {
        return alive;
    }

    public void setAlive(String dado) {
        if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
            this.alive = false;
        } else {
            this.alive = false;
        }
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public String getEyeColour() {
        return eyeColour;
    }

    public void setEyeColour(String eyeColour) {
        this.eyeColour = eyeColour;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Boolean getWizard() {
        return wizard;
    }

    public void setWizard(String dado) {
        if (dado != null && dado.equalsIgnoreCase("VERDADEIRO")) {
            this.wizard = false;
        } else {
            this.wizard = false;
        }
    }

    public String getAncestry() {
        return ancestry;
    }

    public void setAncestry(String ancestry) {
        this.ancestry = ancestry;
    }
}
