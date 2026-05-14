package org.techhub.service;

import java.util.Optional;
import org.techhub.model.AdminLoginModel;

public interface ValidateAdminService {
    Optional<AdminLoginModel> validateAdmin(AdminLoginModel model);
}