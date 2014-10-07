/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import javax.persistence.*;

/**
 *
 * @author xvalusek
 */
@Entity
public class Machine {

    @Id
    @GeneratedValue
    private long id = 0;

    @Column(nullable = false)
    private String name;
}
