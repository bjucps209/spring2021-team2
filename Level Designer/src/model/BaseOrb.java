package model;

public class BaseOrb extends Item{
    
   String type="Orb";
   int healthvalue;

   
public String getType() {
    return type;
}
public void setType(String type) {
    this.type = type;
}
public int getHealthvalue() {
    return healthvalue;
}
public void setHealthvalue(int healthvalue) {
    this.healthvalue = healthvalue;
}
    
}
