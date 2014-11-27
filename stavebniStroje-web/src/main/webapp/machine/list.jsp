<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="s" uri="http://stripes.sourceforge.net/stripes.tld" %>

<s:layout-render name="/layout.jsp" titlekey="index.title">
    <s:layout-component name="body">

        <h2>Seznam stavebních strojů</h2>
        <br />
        <button type="button" class="btn btn-success">
            <span class="glyphicon glyphicon-plus" aria-hidden="true"></span>
            Přidat stroj
        </button>
        &nbsp;&nbsp;&nbsp;&nbsp;
        Zobrazit
        <div class="btn-group" role="group" aria-label="...">
            <button type="button" class="btn btn-default active">Všechny stroje</button>
            <button type="button" class="btn btn-default">Traktory</button>
            <button type="button" class="btn btn-default">Bagry</button>
            <button type="button" class="btn btn-default">Nákladní auta</button>
        </div>

        <br />
        <table class="table table-striped">
            <thead>
                <tr>
                    <th></th>
                    <th>Name</th>
                    <th>Category</th>
                    <th>Borrows</th>
                    <th>Actions</th>
                    <th>Revision</th>
                </tr>
            </thead>
            <tbody>

            <s:useActionBean beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean" var="actionBean"/>

            <table class="table table-striped">
                <thead>
                    <tr>
                        <th></th>
                        <th>Name</th>
                        <th>Půjčeno</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach items="${actionBean.result}" var="customer">

                        <tr>
                            <td><input type="checkbox" name="stroj1" /></td>
                            <td><a href="stranka s podrobnymi informacemi">new Holland T5522</a></td>
                            <td>Traktor</td>
                            <td>Půjčen do 15. 2. 2015
                                <button type="button" class="btn btn-info">
                                    Rezervovat
                                </button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-default">
                                    <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
                                    Upravit
                                </button>
                                <button type="button" class="btn btn-danger">
                                    <span class="glyphicon glyphicon-remove" aria-hidden="true"></span>
                                </button>
                            </td>
                            <td><span style="color: #ff0000; font-weight: bold;">15. 12. 2014</span> 
                                <button type="button" class="btn btn-warning">
                                    <span class="glyphicon glyphicon-wrench" aria-hidden="true"></span>
                                    Přidat revizi
                                </button>
                            </td>
                        </tr>

<!-- ${customer.id} <br /> -->
                    </c:forEach>
                </tbody>
            </table>

            <a href="#">Smazat vybrané</a>

            <nav>
                <ul class="pagination pagination-sm navbar-right">
                    <li><a href="#"><span aria-hidden="true">&laquo;</span><span class="sr-only">Previous</span></a></li>
                    <li><a href="#">1</a></li>
                    <li><a href="#">2</a></li>
                    <li><a href="#">3</a></li>
                    <li><a href="#">4</a></li>
                    <li><a href="#">5</a></li>
                    <li><a href="#"><span aria-hidden="true">&raquo;</span><span class="sr-only">Next</span></a></li>
                </ul>
            </nav>

            <s:form beanclass="cz.muni.fi.stavebnistroje.web.CustomerActionBean">
                <fieldset>
                    <%@include file="form.jsp"%>
                    <s:submit name="add">Vytvořit customera</s:submit>
                </fieldset>
            </s:form>
    </s:layout-component>
</s:layout-render>