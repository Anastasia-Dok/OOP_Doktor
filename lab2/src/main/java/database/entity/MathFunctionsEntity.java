package database.entity;


import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "functions")

public class MathFunctionsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "function_name")
    private String functionName;

    @Column(name = "x_to")
    private Double xTo;

    @Column(name = "x_from")
    private Double xFrom;

    @Column(name = "count")
    private Integer count;

    @OneToMany(mappedBy = "function", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PointEntity> points;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public Double getxTo() {
        return xTo;
    }

    public void setxTo(Double xTo) {
        this.xTo = xTo;
    }

    public Double getxFrom() {
        return xFrom;
    }

    public void setxFrom(Double xFrom) {
        this.xFrom = xFrom;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public List<PointEntity> getPoints() {
        return points;
    }

    public void setPoints(List<PointEntity> points) {
        this.points = points;
    }
}