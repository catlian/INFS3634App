package com.example.infs3634app.model;
/*
Adding ingredient details was a low-priority feature. Need this class for POJO -> Java to work.
 */
class Ingredient {

        private String idIngredient;

        private String strIngredient;

        private String strDescription;

        private String strType;

        private String strAlcohol;

        private String strABV;

        public String getIdIngredient() {
            return idIngredient;
        }

        public void setIdIngredient(String idIngredient) {
            this.idIngredient = idIngredient;
        }

        public String getStrIngredient() {
            return strIngredient;
        }

        public void setStrIngredient(String strIngredient) {
            this.strIngredient = strIngredient;
        }

        public String getStrDescription() {
            return strDescription;
        }

        public void setStrDescription(String strDescription) {
            this.strDescription = strDescription;
        }

        public String getStrType() {
            return strType;
        }

        public void setStrType(String strType) {
            this.strType = strType;
        }

        public String getStrAlcohol() {
            return strAlcohol;
        }

        public void setStrAlcohol(String strAlcohol) {
            this.strAlcohol = strAlcohol;
        }

        public String getStrABV() {
            return strABV;
        }

        public void setStrABV(String strABV) {
            this.strABV = strABV;
        }
}
