<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Equida — Accueil</title>
    <link href="https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700;900&family=DM+Sans:wght@300;400;500&display=swap" rel="stylesheet">
    <style>
        *, *::before, *::after { box-sizing: border-box; margin: 0; padding: 0; }
        :root {
            --cream: #f5f0e8; --brown: #2c1a0e;
            --gold: #b8860b;  --gold-light: #d4a017; --sand: #e8dcc8;
        }
        body { background: var(--cream); color: var(--brown); font-family: 'DM Sans', sans-serif; min-height: 100vh; overflow-x: hidden; }
        header { background: var(--brown); padding: 28px 60px; display: flex; align-items: center; justify-content: space-between; position: relative; }
        header::after { content:''; position:absolute; bottom:0; left:0; right:0; height:4px; background:linear-gradient(90deg,var(--gold),var(--gold-light),var(--gold)); }
        .logo { font-family:'Playfair Display',serif; font-size:2rem; font-weight:900; color:var(--cream); letter-spacing:2px; text-decoration:none; }
        .logo span { color:var(--gold-light); }
        .tagline { color:#a08060; font-size:0.78rem; letter-spacing:3px; text-transform:uppercase; font-weight:300; }
        .hero { background:var(--brown); padding:70px 60px 80px; position:relative; overflow:hidden; }
        .hero::before { content:'🐴'; position:absolute; right:80px; top:50%; transform:translateY(-50%); font-size:9rem; opacity:.08; pointer-events:none; }
        .hero h1 { font-family:'Playfair Display',serif; font-size:clamp(2.2rem,5vw,3.8rem); color:var(--cream); line-height:1.15; max-width:600px; animation:fadeUp .7s ease both; }
        .hero h1 em { color:var(--gold-light); font-style:normal; }
        .hero p { margin-top:18px; color:#b09070; font-size:1.05rem; max-width:480px; line-height:1.6; animation:fadeUp .7s .15s ease both; }
        .grid-section { padding:60px; }
        .section-title { font-family:'Playfair Display',serif; font-size:1.5rem; color:var(--brown); margin-bottom:30px; display:flex; align-items:center; gap:14px; }
        .section-title::after { content:''; flex:1; height:1px; background:var(--sand); }
        .cards { display:grid; grid-template-columns:repeat(auto-fit,minmax(240px,1fr)); gap:24px; }
        .card { background:#fff; border:1px solid var(--sand); border-radius:4px; padding:32px 28px; text-decoration:none; color:var(--brown); display:flex; flex-direction:column; gap:12px; transition:transform .22s ease,box-shadow .22s ease,border-color .22s ease; animation:fadeUp .6s ease both; position:relative; overflow:hidden; }
        .card::before { content:''; position:absolute; top:0; left:0; width:4px; height:100%; background:var(--gold); transform:scaleY(0); transform-origin:bottom; transition:transform .25s ease; }
        .card:hover { transform:translateY(-4px); box-shadow:0 12px 36px rgba(44,26,14,.12); border-color:var(--gold); }
        .card:hover::before { transform:scaleY(1); }
        .card-icon { font-size:2rem; }
        .card-title { font-family:'Playfair Display',serif; font-size:1.15rem; font-weight:700; }
        .card-desc { font-size:.87rem; color:#7a6050; line-height:1.5; }
        .card-badge { display:inline-block; margin-top:6px; font-size:.7rem; font-weight:500; letter-spacing:1.5px; text-transform:uppercase; color:var(--gold); }
        .card:nth-child(1){animation-delay:.1s} .card:nth-child(2){animation-delay:.2s}
        .card:nth-child(3){animation-delay:.3s} .card:nth-child(4){animation-delay:.4s}
        .card:nth-child(5){animation-delay:.5s}
        footer { background:var(--brown); color:#7a6050; text-align:center; padding:20px; font-size:.8rem; letter-spacing:1px; }
        @keyframes fadeUp { from{opacity:0;transform:translateY(20px)} to{opacity:1;transform:translateY(0)} }
    </style>
</head>
<body>

<header>
    <a class="logo" href="<%= request.getContextPath() %>/index.jsp">EQU<span>IDA</span></a>
    <span class="tagline">Système de gestion des ventes aux enchères</span>
</header>

<section class="hero">
    <h1>Bienvenue sur <em>Equida</em></h1>
    <p>Gérez vos ventes aux enchères de chevaux, consultez les fiches équines et suivez les lots en temps réel.</p>
</section>

<section class="grid-section">
    <h2 class="section-title">Modules disponibles</h2>
    <div class="cards">

        <a class="card" href="<%= request.getContextPath() %>/cheval-servlet/list">
            <span class="card-icon">🐎</span>
            <div class="card-title">Chevaux</div>
            <div class="card-desc">Consulter la liste complète des chevaux et accéder à leurs fiches détaillées.</div>
            <span class="card-badge">→ Voir la liste</span>
        </a>

        <a class="card" href="<%= request.getContextPath() %>/vente-servlet/list">
            <span class="card-icon">🏷️</span>
            <div class="card-title">Ventes</div>
            <div class="card-desc">Gérer les ventes aux enchères et consulter les lots associés.</div>
            <span class="card-badge">→ Voir la liste</span>
        </a>

        <a class="card" href="<%= request.getContextPath() %>/course-servlet/list">
            <span class="card-icon">🏁</span>
            <div class="card-title">Courses</div>
            <div class="card-desc">Consulter les courses enregistrées et les résultats de participation des chevaux.</div>
            <span class="card-badge">→ Voir la liste</span>
        </a>

        <a class="card" href="<%= request.getContextPath() %>/cheval-servlet/add">
            <span class="card-icon">➕</span>
            <div class="card-title">Ajouter un cheval</div>
            <div class="card-desc">Enregistrer un nouveau cheval avec son nom, sa race et sa date de naissance.</div>
            <span class="card-badge">→ Formulaire</span>
        </a>

        <a class="card" href="<%= request.getContextPath() %>/vente-servlet/add">
            <span class="card-icon">📋</span>
            <div class="card-title">Ajouter une vente</div>
            <div class="card-desc">Créer une nouvelle vente aux enchères en définissant le lieu et la date.</div>
            <span class="card-badge">→ Formulaire</span>
        </a>

    </div>
</section>

<footer>
    &copy; 2025 Equida &mdash; Gestion des ventes aux enchères équestres
</footer>

</body>
</html>
