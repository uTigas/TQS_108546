package tqs.hw1.fs_webApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FsWebAppApplication {

	public static void main(String[] args) {
		String initializationMode = "never"; // Default initialization mode

		// Check if the custom command-line argument is present
        if (args.length > 0 && "--initialize-data".equals(args[0])) {
            initializationMode = "always";
        }

        // Set the initialization mode as a system property
        System.setProperty("INITIALIZATION_MODE", initializationMode);
		SpringApplication.run(FsWebAppApplication.class, args);
	}

}
