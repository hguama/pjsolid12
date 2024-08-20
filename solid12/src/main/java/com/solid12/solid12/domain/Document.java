package com.solid12.solid12.domain;

import org.apache.commons.lang3.Validate;

import java.util.LinkedList;
import java.util.List;

public class Document {
    private  String documentType;
    private  String documentNumber;
    private  LinkedList<String> documentTypes = new LinkedList<>(List.of("CC", "CE", "PP"));

   public Document(String documentType, String documentNumber) {
      Validate.notEmpty(documentType, "document type can not be empty or null");
      Validate.notEmpty(documentNumber, "document number can not be empty or null");
      validType(documentType);

      this.documentType = documentType;
      this.documentNumber = documentNumber;
   }

   private void validType(String documentType) {
      if(!documentTypes.contains(documentType)) throw new IllegalArgumentException("document type not valid");
   }

    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    @Override
    public String toString() {
        return "Document{" +
                "documentType='" + documentType + '\'' +
                ", documentNumber='" + documentNumber + '\'' +
                ", documentTypes=" + documentTypes +
                '}';
    }
}
