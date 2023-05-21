package com.roboter5123.backend.messaging.model;
import com.roboter5123.backend.game.Command;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
public class CommandStompMessage extends StompMessage{

    Command body;
}
