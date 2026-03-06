package reposetory;
import java.util.*;
import domain.*;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
public class CustomerRepo {
  private Map<String ,Customer> cuById=new HashMap<>();
  
   public List<Customer> findAll(){
  return new ArrayList<>(cuById.values());
  }
  
  public void save(Customer c){
    cuById.put(c.getCustomerId(),c);
  }
  
  
}
