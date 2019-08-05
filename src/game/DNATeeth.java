package game;

public class DNATeeth extends DNAProperty {

    private double damage;
    private int cooldown;

    DNATeeth() {
        super(0xFFFFFFAA);
        damage = 10;
        cooldown = 0;
    }

    public void useProperty(Creature creature){
        Creature target = creature.mind.getTarget();

        if(target != null && cooldown <= 0){
            target.underAttack(damage);
            cooldown = 25;
        }
        else if(cooldown > 0){
            cooldown--;
        }
    }
}
