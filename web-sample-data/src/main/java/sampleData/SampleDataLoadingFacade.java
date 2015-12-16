package sampleData;
import java.io.IOException;

/**
 * Interface for loading Data
 * 
 * @author Martin Cuchran
 */
public interface SampleDataLoadingFacade
{
    /**
     * Loads Sample data
     */
    void loadData() throws IOException;
}
