package com.chatproject.marocz.model;

import com.chatproject.marocz.entities.User;
import org.springframework.web.multipart.MultipartFile;


public class UserForm {



        private String code;




        // Upload file.
        private MultipartFile fileData;
    public UserForm() {


    }


        public UserForm(User user) {


        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }





        public MultipartFile getFileData() {
            return fileData;
        }

        public void setFileData(MultipartFile fileData) {
            this.fileData = fileData;
        }




}
