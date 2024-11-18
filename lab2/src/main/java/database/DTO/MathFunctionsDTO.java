package database.DTO;

public class MathFunctionsDTO {
    private Long id;
    private String functionName;
    private Double xTo;
    private Double xFrom;
    private Integer count;

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
}