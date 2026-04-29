<style>
    .eq-nav {
        background: #2c1a0e;
        padding: 0 30px;
        display: flex;
        align-items: stretch;
        position: fixed;
        top: 0; left: 0; right: 0;
        height: 56px;
        z-index: 9999;
        border-bottom: 3px solid #b8860b;
        font-family: 'DM Sans', 'Segoe UI', sans-serif;
    }
    .eq-nav a {
        text-decoration: none;
        color: #c8a878;
        font-size: 0.87rem;
        letter-spacing: 0.5px;
        display: flex;
        align-items: center;
        padding: 0 18px;
        transition: background 0.18s, color 0.18s;
        white-space: nowrap;
    }
    .eq-nav .eq-brand {
        font-weight: 700;
        font-size: 1.1rem;
        letter-spacing: 3px;
        color: #f5f0e8;
        padding-right: 28px;
        border-right: 1px solid #4a3020;
        margin-right: 8px;
    }
    .eq-nav .eq-brand span { color: #d4a017; }
    .eq-nav a:not(.eq-brand):hover {
        background: #3d2510;
        color: #f5f0e8;
    }
    .eq-nav .eq-sep {
        width: 1px;
        background: #4a3020;
        margin: 12px 4px;
    }
    .eq-nav .eq-spacer { flex: 1; }
    body { padding-top: 56px !important; }
</style>

<nav class="eq-nav">
    <a class="eq-brand" href="<%= request.getContextPath() %>/index.jsp">EQU<span>IDA</span></a>
    <a href="<%= request.getContextPath() %>/cheval-servlet/list">Chevaux</a>
    <a href="<%= request.getContextPath() %>/cheval-servlet/add">+ Cheval</a>
    <div class="eq-sep"></div>
    <a href="<%= request.getContextPath() %>/vente-servlet/list">Ventes</a>
    <a href="<%= request.getContextPath() %>/vente-servlet/add">+ Vente</a>
    <div class="eq-sep"></div>
    <a href="<%= request.getContextPath() %>/course-servlet/list">Courses</a>
    <a href="<%= request.getContextPath() %>/course-servlet/add">+ Course</a>
    <div class="eq-sep"></div>
    <a href="<%= request.getContextPath() %>/index.jsp">Accueil</a>
</nav>
