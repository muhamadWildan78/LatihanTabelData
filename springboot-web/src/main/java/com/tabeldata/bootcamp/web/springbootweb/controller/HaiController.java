package com.tabeldata.bootcamp.web.springbootweb.controller;

import com.tabeldata.bootcamp.web.springbootweb.dao.CategoryDao;
import com.tabeldata.bootcamp.web.springbootweb.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

    @RestController
    @RequestMapping("/category/api")
    public class HaiController {

        @Autowired
        private CategoryDao dao;

        @GetMapping("/category/list")
        public List<Category> list() {
            return dao.list();
        }

        @GetMapping("/findById/{kodeid}")
        public ResponseEntity<?> findById(@PathVariable String id) {
            try {
                Category data = this.dao.findById(id);
                return ResponseEntity.ok(data);
            } catch (EmptyResultDataAccessException erdae) {
                return ResponseEntity.noContent().build();
            }
        }

        @GetMapping(value = "/category/show")
        public Category showData(
                @RequestParam(name = "ctg") String category,
                @RequestParam(name = "dpt") String department,
                @RequestParam(name = "nme") String name,
                @RequestParam(name = "dsc") String description){
            Category data = new Category();
            data.setCategory_id(category);
            data.setDepartment_id(department);
            data.setName(name);
            data.setDescription(description);
            return data;
        }

        @PostMapping(value = "/category/input")
        public ResponseEntity<?> inputData(@RequestBody @Valid Category data) {
            try {
                this.dao.insertCategory(data);
                return ResponseEntity.ok().build();
            } catch (DuplicateKeyException dke) {
                dke.printStackTrace();
                return ResponseEntity.badRequest()
                        .body("Duplicate data");
            }catch (DataAccessException dea){
                dea.printStackTrace();
                return ResponseEntity.internalServerError()
                        .body("database gak konek atau sql salah");
            }
            catch (Exception ex){
                ex.printStackTrace();
                return ResponseEntity.internalServerError()
                        .body("Gak tau errornya apa! check sendiri");
            }
        }

        @DeleteMapping("/category/{kodeid}")
        public ResponseEntity<?> delete(@PathVariable String kodeid) {
            this.dao.deleteCategory(kodeid);
            return ResponseEntity.ok().build();
        }
    }