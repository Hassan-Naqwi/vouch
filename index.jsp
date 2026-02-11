<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vouch</title>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>
</head>

<body>
    <% String role=(String)session.getAttribute("role"); if (role !=null) { if(role.equals("user")) {%>
        <jsp:forward page="userDashboard/Dashboard.jsp" />
        <% }else {%>
            <jsp:forward page="adminDashboard/Dashboard.jsp" />
            <% } } %>
                <section>
                    <div class="container mx-auto">
                        <div class="flex flex-col justify-center items-center h-screen">
                            <h1 class="text-5xl font-bold text-center text-blue-700">Welcome to Vouch</h1>
                            <h1 class="text-xl font-semibold text-center">Internal Commmunication Platform</h1>
                            <form class="gap-3 mt-3 flex flex-col " method="post" action="controller?action=login">
                                <input class="border-2 border-blue-500 focus:outline-none rounded-md p-2 pl-4"
                                    type="email" placeholder="Email . . ." name="email">
                                <input class="border-2 border-blue-500 focus:outline-none rounded-md p-2 pl-4"
                                    type="password" placeholder="Password . . ." name="password">
                                <input class="rounded-md cursor-pointer bg-blue-600 w-full text-white px-5 py-2"
                                    type="submit" value="Login to your account">
                                <div class="flex w-full justify-between">
                                    <p class="text-[12px]">New User?</p>
                                    <a href="signup.jsp" class="text-red-600 font-semibold text-[12px]">Create
                                        Account
                                        Here</a>
                                </div>
                                <% String message=(String)request.getParameter("message"); String msg="" ; if(message
                                    !=null) { if(message.equals("failed")) msg="Wrong Email or Password" ; else if
                                    (message.equals("success")) msg="Account Created" ; else if
                                    (message.equals("logout")) msg="Logout Successful" ; else msg="" ; } %>
                                    <p class="text-red-700 mt-3 text-center text-lg">
                                        <%= msg %>
                                    </p>
                            </form>
                        </div>
                    </div>
                </section>


</body>

</html>