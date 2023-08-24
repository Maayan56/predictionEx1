package system.engine.world.creation.impl.rule.action;

import system.engine.world.creation.api.ActionCreation;
import system.engine.world.definition.entity.api.EntityDefinition;
import system.engine.world.definition.entity.manager.api.EntityDefinitionManager;
import system.engine.world.rule.action.api.Action;
import system.engine.world.rule.action.impl.KillAction;
import system.engine.world.rule.action.impl.SetAction;
import system.engine.world.rule.action.impl.condition.ConditionAction;
import system.engine.world.rule.action.impl.condition.MultipleConditionActionAction;
import system.engine.world.rule.action.impl.condition.SingleConditionActionAction;
import system.engine.world.rule.action.impl.numeric.impl.DecreaseAction;
import system.engine.world.rule.action.impl.numeric.impl.IncreaseAction;
import system.engine.world.rule.action.impl.numeric.impl.calculation.DivideAction;
import system.engine.world.rule.action.impl.numeric.impl.calculation.MultiplyAction;

import java.util.Arrays;
import java.util.List;

public class ActionCreationImpl implements ActionCreation {

    public Action createActionIncrease(EntityDefinition entityDefinition, String propertyName, String expressionStr){
        return new IncreaseAction(entityDefinition, propertyName, expressionStr);
    }

    public Action createActionDecrease(EntityDefinition entityDefinition, String propertyName, String expressionStr){
        return new DecreaseAction(entityDefinition, propertyName, expressionStr);
    }


    public Action createActionCalculationDivide(EntityDefinition entityDefinition, String resultPropertyName,
                String expressionStr1,String expressionStr2 ){
        return new DivideAction(entityDefinition, resultPropertyName, expressionStr1, expressionStr2);
    }

    public Action createActionCalculationMultiply(EntityDefinition entityDefinition, String resultPropertyName,
                String expressionStr1,String expressionStr2){
        return new MultiplyAction(entityDefinition, resultPropertyName, expressionStr1, expressionStr2);
    }


    public Action createActionSet(EntityDefinition entityDefinition, String propertyName, String expressionStr){
        return new SetAction(entityDefinition, propertyName, expressionStr);
    }

    public Action createActionKill(EntityDefinition entityDefinition){
        return new KillAction(entityDefinition);
    }
}
