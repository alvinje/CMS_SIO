/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cms_sio.model;

/**
 *
 * @author sgoyet
 */
public enum Multiplicity {

    _1 {
                public String toString() {
                    return "_1";
                }
            },
    _1_n {
                public String toString() {
                    return "_1_n";
                }
            },
    _0_n {
                public String toString() {
                    return "_0_n";
                }
            },
    _0_1 {
                public String toString() {
                    return "_0_1";
                }
            },


}
