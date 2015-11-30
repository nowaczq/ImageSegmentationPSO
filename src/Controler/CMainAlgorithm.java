package Controler;

import Model.MPSOAlgorithm;

/**
 * Created by dom on 2015-10-28.
 */
public class CMainAlgorithm
{
    private MPSOAlgorithm algorithm;
    private CLogicControler logicControler;

    public CMainAlgorithm ()
    {
        this.algorithm = new MPSOAlgorithm(this.logicControler.getImage());
    }


}
