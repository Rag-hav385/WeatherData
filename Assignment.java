import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.File;


/**
 * Write a description of Assignment here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Assignment {
    
    public String FileName;
    public CSVRecord coldesthourinfile(CSVParser parser){
        CSVRecord SmallestSoFar = null;
        for (CSVRecord currentRow : parser){
            
            SmallestSoFar = getsmallestoftwo(currentRow , SmallestSoFar);
            
    }
    return SmallestSoFar;
    }
    
    public CSVRecord getsmallestoftwo (CSVRecord currentRow , CSVRecord Smallest){
        if(Smallest == null){
            Smallest = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double SmallestTemp = Double.parseDouble(Smallest.get("TemperatureF"));
            
            if(currentTemp < SmallestTemp){
                Smallest = currentRow;
            }
            
        }
        return Smallest;
}

public void Testcoldesthourinfile(){
    FileResource fr = new FileResource();
    CSVRecord smallest = coldesthourinfile(fr.getCSVParser());
    System.out.println("Coldest Temperarture was " + smallest.get("TemperatureF") + " with humidty " + smallest.get("Humidity")); 
}

public CSVRecord FileWithColdestTemp(){
    CSVRecord SmallestSoFar = null;
    
    DirectoryResource dr = new DirectoryResource();
    
    for(File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        CSVRecord currentRow = coldesthourinfile(fr.getCSVParser());
        if(SmallestSoFar == null){
            SmallestSoFar = currentRow;
        }
        else{
            double currentTemp = Double.parseDouble(currentRow.get("TemperatureF"));
            double SmallestTemp = Double.parseDouble(SmallestSoFar.get("TemperatureF"));
            
            if(currentTemp < SmallestTemp){
                SmallestSoFar = currentRow;
                FileName = f.getName();
            }
            
        }
        
        
    }
    return SmallestSoFar;
}
public void TestFileWithColdest(){
    CSVRecord smallest = FileWithColdestTemp();
    System.out.println("Coldest Temperarture was at " + smallest.get("TemperatureF") + "on" + FileName); 

}

public CSVRecord lowesthumidityinfile(CSVParser parser){
        CSVRecord SmallestSoFar = null;
        for (CSVRecord currentRow : parser){
            
            SmallestSoFar = getsmallestoftwo_humidity(currentRow , SmallestSoFar);
            
    }
    return SmallestSoFar;
    }
    
    public CSVRecord getsmallestoftwo_humidity (CSVRecord currentRow , CSVRecord Smallest){
        if(Smallest == null){
            Smallest = currentRow;
        }
        else{
            
            String currenthumid = currentRow.get("Humidity");
            String Smallesthumid = Smallest.get("Humidity");
            
            if (currenthumid == "N/A" || Smallesthumid == " N/A"){
                currenthumid = currenthumid;
                Smallesthumid = Smallesthumid;
            }
            else{
                double currenthumid_no = Double.parseDouble(currenthumid);
                double Smallesthumid_no = Double.parseDouble(Smallesthumid);
                if(currenthumid_no < Smallesthumid_no){
                    Smallest = currentRow;
                }
            }
        
        
    }
    return Smallest;
}
public void Testlowesthumidityinfile(){
    FileResource fr = new FileResource();
    CSVRecord smallest = lowesthumidityinfile(fr.getCSVParser());
    System.out.println("Lowest Humidity : " + smallest.get("Humidity") + "at" + smallest.get("DateUTC")); 
}

public CSVRecord lowesthumidityinmanyfile(){
    CSVRecord SmallestSoFar = null;
    
    DirectoryResource dr = new DirectoryResource();
    
    for(File f : dr.selectedFiles()){
        FileResource fr = new FileResource(f);
        CSVRecord currentRow = lowesthumidityinfile (fr.getCSVParser());
        
        SmallestSoFar = getsmallestoftwo_humidity(currentRow , SmallestSoFar); 
        
    }
    return SmallestSoFar;
}

public void Testlowesthumidityinmanyfile(){
    
    CSVRecord smallest = lowesthumidityinmanyfile();
    System.out.println("Lowest Humidity : " + smallest.get("Humidity") + "at" + smallest.get("DateUTC")); 
}

public double averageoftempvalues(CSVParser parser , double value){
    double sum = 0.0;
    double num = 0.0;
    for(CSVRecord record:parser){
        String temp = record.get("TemperatureF");
        if (temp == "9999"){
         temp = temp;
        }
        else{
             String Val = record.get("Humidity");
            if(Val == "N/A"){
                 value = value;
            }
           else{
            value = Double.parseDouble(record.get("Humidity")); 
            
            if( value >= 80){
                double temperature = Double.parseDouble(temp);
                sum = sum + temperature;
            
                num = num + 1;
            }
          }
        }
         
      }
      return sum / num;
    }
    
    
public void Testaveragegeoftempvalues(){
    FileResource fr = new FileResource();
    double average = averageoftempvalues(fr.getCSVParser() , 80.0);
    System.out.print ("Average :" + average);
}
    
   
}





