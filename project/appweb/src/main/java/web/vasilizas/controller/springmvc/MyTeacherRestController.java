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
import vasilizas.bean.db.TeacherDb;
import web.vasilizas.repositories.orm.SpringOrmTeacherRepository;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/teacher-rest", produces = "application/json")
public class MyTeacherRestController {

    private final SpringOrmTeacherRepository teacherRepository;

    @GetMapping
    public List<TeacherDb> getAll() {
        return teacherRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeacherDb> getById(@PathVariable int id) {
        var student = teacherRepository.find(id);
        return ResponseEntity.of(student);
    }

    @PostMapping
    public ResponseEntity<TeacherDb> saveTeacher(@RequestBody TeacherDb teacher) {
        teacherRepository.addPersonInDb(teacher);
        return ResponseEntity.ok(teacher);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTeacher(
            @PathVariable int id,
            @RequestBody TeacherDb teacher) {
        if (teacher != null && id != teacher.getId()) {
            return ResponseEntity
                    .badRequest()
                    .body("Student id must be equal with id in path: " + id + " != " + teacher.getId());
        }
        return ResponseEntity.ok(teacherRepository.save(teacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<TeacherDb> deleteTeacher(@PathVariable int id) {
        var teacher = teacherRepository.find(id);
        if (teacher.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        teacherRepository.remove(id);
        return ResponseEntity.of(teacher);
    }

    @PostMapping("/deletegroup")
    public ResponseEntity<TeacherDb> deleteGroup(@RequestParam(value = "id", required = false) String id) {
        var teacher = teacherRepository.find(Integer.parseInt(id)).orElseThrow();
        teacher.setGroup(null);
        teacherRepository.save(teacher);
        return ResponseEntity.ok(teacher);
    }
}
