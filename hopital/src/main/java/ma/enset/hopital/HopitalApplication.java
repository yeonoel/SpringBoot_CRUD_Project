package ma.enset.hopital;

import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;

@SpringBootApplication
public class HopitalApplication implements  CommandLineRunner {
	
	@Autowired
	private PatientRepository patientRepository;
	public static void main(String[] args) {
		SpringApplication.run(HopitalApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		patientRepository.save(new Patient(null, "Light", new Date(), false, 7734));
		patientRepository.save(new Patient(null, "Mohad", new Date(), true, 2234));
		patientRepository.save(new Patient(null, "YeoKING", new Date(), false, 7534));
		patientRepository.save(new Patient(null, "LiRim", new Date(), false, 1134));
		patientRepository.save(new Patient(null, "kamel", new Date(), true, 6434));

	}

}
