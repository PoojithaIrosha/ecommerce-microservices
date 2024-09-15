package com.poojithairosha.notification.repository;

import com.poojithairosha.common.notification.dto.PaymentConfirmation;
import com.poojithairosha.notification.entity.Notification;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface NotificationRepository extends MongoRepository<Notification, String> {
}
