package cz.fel.sep.persistence.classes;

import java.io.Serializable;
import java.math.BigInteger;

public interface IEntity extends Serializable {

    public BigInteger getId();

    public void setId(BigInteger id);
}
