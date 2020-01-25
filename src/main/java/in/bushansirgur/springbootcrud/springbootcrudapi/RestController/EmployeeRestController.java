package in.bushansirgur.springbootcrud.springbootcrudapi.RestController;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.bushansirgur.springbootcrud.springbootcrudapi.RestService.RestServiceConfig;
import in.bushansirgur.springbootcrud.springbootcrudapi.model.Employee;

@RestController
@RequestMapping("/v1/api")
public class EmployeeRestController {

	@Autowired
	private RestServiceConfig config;

	@GetMapping("/list")
	public List<Employee> getAlls() {
		return config.findAlls();
	}

	@PostMapping("/register")
	public ResponseEntity<String> resgistration(@Valid @RequestBody Employee employee) {
		config.createData(employee);
		return new ResponseEntity<String>("Register Successfull!", HttpStatus.OK);
	}

	@GetMapping("/byId/{id}")
	public ResponseEntity<Employee> getById(@PathVariable("id") Integer id) {
		Employee employee = config.findById(id);
		if (employee == null) {
			return new ResponseEntity<Employee>(employee, HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<Employee>(employee, HttpStatus.OK);
		}
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> updateRecord(@PathVariable("id") Integer id, @Valid @RequestBody Employee update) {
		Employee employee = config.findById(update.getId());
		if (employee == null) {
			return new ResponseEntity<String>("Not Found", HttpStatus.NOT_FOUND);
		}
		employee.setName(update.getName());
		employee.setDepartment(update.getDepartment());
		employee.setDob(update.getDob());
		employee.setGender(update.getGender());
		config.createData(employee);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletRecord(@PathVariable("id") Integer id) {
		config.deleteData(id);
		return new ResponseEntity<String>(HttpStatus.OK);
	}

}
