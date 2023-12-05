package ma.enset.hopital.web;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import ma.enset.hopital.entities.Patient;
import ma.enset.hopital.repository.PatientRepository;

@Controller
@AllArgsConstructor
public class PatientControler {
	private PatientRepository patientRepository;
	
	@GetMapping("/index")
	public String index(Model model, @RequestParam(name="page", defaultValue = "0") int page, 
									 @RequestParam(name="size", defaultValue="8") int size,
									 @RequestParam(name="keyword", defaultValue = "") String kw) {
									 
		Page<Patient> patientList = patientRepository.findByNomContains(kw, PageRequest.of(page, size));
		model.addAttribute("listPatients", patientList);
		model.addAttribute("pages", new int[patientList.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("kw", kw);
		return "patients";
	}
	
	@GetMapping("/delete")
	public String delete(Long id, String keyword, int page) {
		patientRepository.deleteById(id);
		return "redirect:/index?page="+page+"&keyword="+keyword;
	}
	@GetMapping("/")
	public String home() { return "redirect:/index";}

	@GetMapping("/formulairepatient")
	public String formulairepatient(Model model) {
		model.addAttribute("patient", new Patient());
		return "formulairepatient";
	}
	@PostMapping("/savePatient")
	public String savePatient(@Valid Patient patient, BindingResult bindingResult) {
		if (bindingResult.hasErrors()) {
			return "formulairepatient";
		}
		patientRepository.save(patient);
		return "redirect:/index?keyword="+patient.getNom();
	}
	
	@GetMapping("/editePatient")
	public String editePatient(Model model, @RequestParam(name = "id") Long id) {
		Patient patient = patientRepository.findById(id).get();
		model.addAttribute("patient", patient);
		return "editePatient";
	}
}
