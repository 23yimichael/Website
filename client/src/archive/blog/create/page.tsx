import { readUserByToken } from "../../../services/auth";
import User from "../../../types/dto/User";
import Client from "./client";

const Create = async () => {
  const token = await localStorage.getItem("token");
  let authorized = false;

  if (token) {
    const res = await readUserByToken(token);
    if (res.status === 200) {
      const user: User = await res.json();

      if (user.authorities[0].authority.includes("ROLE_ADMIN"))
        authorized = true;
    }
  }

  return <Client authorized={authorized} />;
};

export default Create;
