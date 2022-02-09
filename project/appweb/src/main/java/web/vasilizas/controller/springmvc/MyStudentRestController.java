package web.vasilizas.controller.springmvc;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vasilizas.bean.db.StudentDb;
import web.vasilizas.repositories.orm.SpringOrmStudentRepository;

import java.util.List;

import static java.lang.Integer.parseInt;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/student-rest", produces = "application/json")
public class MyStudentRestController {

    private final SpringOrmStudentRepository studentRepository;

    @GetMapping
    public List<StudentDb> getAll() {
        return studentRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDb> getById(@PathVariable int id) {
        var student = studentRepository.find(id);
        return ResponseEntity.of(student);
    }

    @PostMapping
    public ResponseEntity<StudentDb> saveStudent(@RequestBody StudentDb student) {
        studentRepository.addPersonInDb(student);
        return ResponseEntity.ok(student);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateStudent(
            @PathVariable int id,
            @RequestBody StudentDb student) {
        if (student != null && id != student.getId()) {
            return ResponseEntity
                    .badRequest()
                    .body("Student id must be equal with id in path: " + id + " != " + student.getId());
        }
        return ResponseEntity.ok(studentRepository.save(student));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<StudentDb> deleteStudent(@PathVariable int id) {
        var student = studentRepository.find(id);
        if (student.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        studentRepository.remove(id);
        return ResponseEntity.of(student);
    }

    @PostMapping("/addgroup")
    public ResponseEntity<StudentDb> addGroup(@RequestParam(value = "id", required = false) String id,
                                              @RequestParam(value = "group", required = false) String groupId) {
        return ResponseEntity.ok(studentRepository.addGroup(parseInt(id), parseInt(groupId)));
    }

    @PostMapping("/deletegroup")
    public ResponseEntity<StudentDb> deleteGroup(@RequestParam(value = "id", required = false) String id,
                                                 @RequestParam(value = "group", required = false) String groupId) {
        return ResponseEntity.ok(studentRepository.removeGroup(parseInt(id), parseInt(groupId)));
    }
}
