package com.example.fitmatch.model;

<<<<<<< HEAD
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Service{
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String className;
    private String classDesc;

    public Long getId(){
        return id;
    }

public String getName(String className){
    return className;
}

public void setName(String className){
    this.className = className;
}

public String getDescription(){
    return classDesc;
}

public void setDescription(String classDesc){
    this.classDesc =  classDesc;
}

=======
import jakarta.persistence.*;

@Entity
@Table(name = "services")
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trainer_id", nullable = false)
    private TrainerProfile trainer;

    private String name;

    @Column(length = 1000)
    private String description;

    private Integer duration;
    private Double price;
    private String category;
    private Boolean isActive = true;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public TrainerProfile getTrainer() { return trainer; }
    public void setTrainer(TrainerProfile trainer) { this.trainer = trainer; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getDuration() { return duration; }
    public void setDuration(Integer duration) { this.duration = duration; }
    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public Boolean getIsActive() { return isActive; }
    public void setIsActive(Boolean isActive) { this.isActive = isActive; }
>>>>>>> c18d6b7f1eb1ec89673dddebc53457c17c6307d9
}