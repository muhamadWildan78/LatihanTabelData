package com.tabeldata.bootcamp.web.springbootweb.model;

import lombok.Data;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

    @Data
    public class Category {
        @NotNull(message = "gak boleh null")
        private String category_id;
        @NotNull(message = "gak boleh null")
        @NotEmpty(message = "gak boleh kosong")
        private String department_id;
        private String name;
        private String description;
    }
