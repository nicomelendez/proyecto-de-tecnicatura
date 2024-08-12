<%
if (request.getSession().getAttribute("mostrarModal") != null) {
%>
<div class="modal mostrar"> <!-- Agregar la clase 'mostrar' -->
    <h3><%=request.getSession().getAttribute("tituloModal")%></h3>
    <p><%=request.getSession().getAttribute("descripcionModal")%></p>
    <div class="modalContenido">
        <form action="<%=request.getSession().getAttribute("urlBtnSiModal")%>"
              method="<%=request.getSession().getAttribute("metodoModal")%>">
            <button type="submit" class="btnSi">Si</button>
        </form>
        <form action="/Proyecto-PInfra/SvCerrarModal" method="GET">
            <button type="submit" class="btnNo">No</button>
        </form>
    </div>
</div>
<%
}
%>
