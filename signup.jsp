<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vouch</title>
    <script src="https://cdn.jsdelivr.net/npm/@tailwindcss/browser@4"></script>

</head>

<body>

    <section>
        <div class="container mx-auto">
            <div class="flex flex-col justify-center items-center h-screen">
                <h1 class="text-5xl font-bold text-center text-blue-700">Welcome to Vouch</h1>
                <h1 class="text-xl text-center font-semibold">Internal Commmunication Platform</h1>
                <form method="post" class="gap-3 mt-3 w-fit flex flex-col" action="controller?action=signup">
                    <input class="border-2 border-blue-500 focus:outline-none rounded-md p-2 pl-4" type="text"
                        placeholder="Your Name . . ." name="name">
                    <input class="border-2 border-blue-500 focus:outline-none rounded-md p-2 pl-4" type="email"
                        placeholder="Your Email . . ." name="email">
                    <div class="flex justify-between w-full items-center">
                        <div class="flex gap-2">
                            <input type="radio" value="admin" name="role">
                            <label for="">Admin</label>
                        </div>
                        <div class="flex gap-2">
                            <input type="radio" value="user" name="role">
                            <label for="">User</label>
                        </div>
                    </div>
                    <input class="border-2 border-blue-500 focus:outline-none rounded-md p-2 pl-4" type="password"
                        placeholder="Your Password . . ." name="password">
                    <input class="cursor-pointer bg-red-600 text-white py-2 rounded-md" type="submit"
                        value="Create Your Account">
                    <div class="flex  justify-between">
                        <p class="text-[12px]">Already have an account?</p>
                        <a href="/vouch/controller?action=index" class="text-blue-600 font-semibold text-[12px]">Sign in Here</a>
                    </div>
                </form>
            </div>
        </div>
    </section>

</body>

</html>