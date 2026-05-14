package org.techhub.respository;

import java.util.Optional;
import org.techhub.model.AdminLoginModel;

public interface ValidateAdminRepo {
    Optional<AdminLoginModel> validateAdmin(AdminLoginModel model);
}