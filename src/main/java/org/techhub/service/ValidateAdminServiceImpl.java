package org.techhub.service;

import java.util.Optional;

import org.techhub.model.AdminLoginModel;
import org.techhub.respository.ValidateAdminRepo;
import org.techhub.respository.ValidateAdminRepoImpl;

public class ValidateAdminServiceImpl implements ValidateAdminService {

    ValidateAdminRepo validateAdminRepo;

    @Override
    public Optional<AdminLoginModel> validateAdmin(AdminLoginModel model) {
        validateAdminRepo = new ValidateAdminRepoImpl();
        return validateAdminRepo.validateAdmin(model);
    }
}