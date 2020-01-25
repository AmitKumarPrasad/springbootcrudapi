package in.bushansirgur.springbootcrud.springbootcrudapi.RestService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.bushansirgur.springbootcrud.springbootcrudapi.EmployeeRepository.EmployeeRepository;
import in.bushansirgur.springbootcrud.springbootcrudapi.model.Employee;

@Service
public class RestServiceConfig {
	@Autowired
	private EmployeeRepository repository;

	public void createData(Employee employee) {
		repository.save(employee);
	}

	public List<Employee> findAlls() {
		return repository.findAll();
	}

	public Employee findById(Integer id) {
		return repository.getOne(id);
	}

	public void updateDat(Employee employee) {
		Employee update = repository.getOne(employee.getId());
		update.setName(employee.getName());
		update.setGender(employee.getGender());
		update.setDepartment(employee.getDepartment());
		update.setDob(employee.getDob());
		repository.save(update);
	}

	public void deleteData(Integer id) {
		repository.deleteById(id);
	}

}
